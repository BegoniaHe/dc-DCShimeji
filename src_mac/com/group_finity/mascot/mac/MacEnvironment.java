package com.group_finity.mascot.mac;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.management.ManagementFactory;

import com.sun.jna.Pointer;
import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

import com.group_finity.mascot.environment.Area;
import com.group_finity.mascot.environment.Environment;
import com.group_finity.mascot.mac.jna.Carbon;
import com.group_finity.mascot.mac.jna.ProcessSerialNumber;
import com.group_finity.mascot.mac.jna.AXValueRef;
import com.group_finity.mascot.mac.jna.AXUIElementRef;
import com.group_finity.mascot.mac.jna.CFTypeRef;
import com.group_finity.mascot.mac.jna.CGPoint;
import com.group_finity.mascot.mac.jna.CGSize;
import com.group_finity.mascot.mac.jna.CFStringRef;
import com.group_finity.mascot.mac.jna.CFNumberRef;
import com.group_finity.mascot.mac.jna.CFArrayRef;

/**
 * 使用 Accessibility API 获取 Java 难以获取的环境信息。
 */
class MacEnvironment extends Environment {

  /**
   * 在 Mac 上，可以获取活动窗口，使 Shimeji 能对其做出反应。
   * <p>
   * 因此，在此类中，将 activeIE 命名为 frontmostWindow 的别名。
   */
	private static Area activeIE = new Area();
  private static Area frontmostWindow = activeIE;

	private static final int screenWidth =
		(int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
	private static final int screenHeight =
		(int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight());

	private static Carbon carbon = Carbon.INSTANCE;

// 在 Mac 上，ManagementFactory.getRuntimeMXBean().getName() 会返回 PID@主机名 的字符串
	private static long myPID =
		Long.parseLong(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);

	private static long currentPID = myPID;

	private static HashSet<Long> touchedProcesses = new HashSet<Long>();

	static final CFStringRef
	kAXPosition = createCFString("AXPosition"),
		kAXSize = createCFString("AXSize"),
		kAXFocusedWindow = createCFString("AXFocusedWindow"),
		kDock = createCFString("com.apple.Dock"),
		kTileSize = createCFString("tilesize"),
		kOrientation = createCFString("orientation"),
		kAXChildren = createCFString("AXChildren");

  private static Rectangle getFrontmostAppRect() {
		Rectangle ret;
		long pid = getCurrentPID();

		AXUIElementRef application =
			carbon.AXUIElementCreateApplication(pid);

		PointerByReference windowp = new PointerByReference();

		// Check if AXUIElementCopyAttributeValue succeeded (returns kAXErrorSuccess).
		// If the call fails, ret will be set to null to indicate no valid window was found.
		if (carbon.AXUIElementCopyAttributeValue(
					application, kAXFocusedWindow, windowp) == Carbon.kAXErrorSuccess) {
			AXUIElementRef window = new AXUIElementRef();
			window.setPointer(windowp.getValue());
			ret = getRectOfWindow(window);
		} else {
			ret = null;
		}

		carbon.CFRelease(application);
		return ret;
  }

	private static long getFrontmostAppsPID() {
		ProcessSerialNumber frontProcessPsn = new ProcessSerialNumber();
		LongByReference frontProcessPidp = new LongByReference();

		carbon.GetFrontProcess(frontProcessPsn);
		carbon.GetProcessPID(frontProcessPsn, frontProcessPidp);

		return frontProcessPidp.getValue();
	}

	private static CGPoint getPositionOfWindow(AXUIElementRef window) {
		CGPoint position = new CGPoint();
		AXValueRef axvalue = new AXValueRef();
		PointerByReference valuep = new PointerByReference();

		carbon.AXUIElementCopyAttributeValue(window, kAXPosition, valuep);
		axvalue.setPointer(valuep.getValue());
		carbon.AXValueGetValue(axvalue, Carbon.kAXValueCGPointType, position.getPointer());
		position.read();

		return position;
	}

	private static CGSize getSizeOfWindow(AXUIElementRef window) {
		CGSize size = new CGSize();
		AXValueRef axvalue = new AXValueRef();
		PointerByReference valuep = new PointerByReference();

		carbon.AXUIElementCopyAttributeValue(window, kAXSize, valuep);
		axvalue.setPointer(valuep.getValue());
		carbon.AXValueGetValue(axvalue, Carbon.kAXValueCGSizeType, size.getPointer());
		size.read();

		return size;
	}

	private static void moveFrontmostWindow(final Point point) {
		AXUIElementRef application =
			carbon.AXUIElementCreateApplication(currentPID);

		PointerByReference windowp = new PointerByReference();

		if (carbon.AXUIElementCopyAttributeValue(
					application, kAXFocusedWindow, windowp) == Carbon.kAXErrorSuccess) {
			AXUIElementRef window = new AXUIElementRef();
			window.setPointer(windowp.getValue());
			moveWindow(window, (int) point.x, (int) point.y);
		}

		carbon.CFRelease(application);
	}

	private static void restoreWindowsNotIn(final Rectangle rect) {
		Rectangle visibleArea = getWindowVisibleArea();
		for (long pid : getTouchedProcesses()) {
			AXUIElementRef application =
				carbon.AXUIElementCreateApplication(pid);

			for (AXUIElementRef window : getWindowsOf(application)) {
				carbon.CFRetain(window);
				Rectangle windowRect = getRectOfWindow(window);
				if (!visibleArea.intersects(windowRect)) {
					moveWindow(window, 0, 0);
				}
				carbon.CFRelease(window);
			}

			carbon.CFRelease(application);
		}
	}

	private static ArrayList<AXUIElementRef> getWindowsOf(AXUIElementRef application) {
		PointerByReference axWindowsp = new PointerByReference();
		ArrayList<AXUIElementRef> ret = new ArrayList<AXUIElementRef>();

		carbon.AXUIElementCopyAttributeValue(application, kAXChildren, axWindowsp);

		if (axWindowsp.getValue() == Pointer.NULL) {
			return ret;
		}

		CFArrayRef cfWindows = new CFArrayRef();
		cfWindows.setPointer(axWindowsp.getValue());

		for (long i = 0, l = carbon.CFArrayGetCount(cfWindows); i < l; ++i) {
			Pointer p = carbon.CFArrayGetValueAtIndex(cfWindows, i);
			AXUIElementRef el = new AXUIElementRef();
			el.setPointer(p);
			ret.add(el);
		}

		return ret;
	}

	private static Rectangle getRectOfWindow(AXUIElementRef window) {
		CGPoint pos = getPositionOfWindow(window);
		CGSize size = getSizeOfWindow(window);
		return new Rectangle(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
	}

	private static void moveWindow(AXUIElementRef window, int x, int y) {
		CGPoint position = new CGPoint((double) x, (double) y);
		position.write();
		AXValueRef axvalue = carbon.AXValueCreate(
			Carbon.kAXValueCGPointType, position.getPointer());
		carbon.AXUIElementSetAttributeValue(window, kAXPosition, axvalue);
	}

	private static CFStringRef createCFString(String s) {
		return carbon.CFStringCreateWithCharacters(null, s.toCharArray(), s.length());
	}

	private static int getScreenWidth() {
		return screenWidth;
	}

	private static int getScreenHeight() {
		return screenHeight;
	}

/**
 * 当 min < max 时：
 * <ul>
 *   <li>如果 min <= x <= max，则返回 x</li>
 *   <li>如果 x < min，则返回 min</li>
 *   <li>如果 x > max，则返回 max</li>
 * </ul>
 */
	private static double betweenOrLimit(double x, double min, double max) {
		return Math.min(Math.max(x, min), max);
	}

/**
 * 返回窗口在屏幕内移动时不会被弹回的范围（Rectangle）。
 * <p>
 * 在 Mac 上，如果尝试将窗口完全移出屏幕，窗口会被强制留在屏幕内。
 */
	private static Rectangle getWindowVisibleArea() {
		final int menuBarHeight = 22;
		int x = 1, y = menuBarHeight,
			width = getScreenWidth() - 2, // 0-origin なので
			height = getScreenHeight() - menuBarHeight;

		refreshDockState();
		final String orientation = getDockOrientation();
		final int tilesize = getDockTileSize();

		if ("bottom".equals(orientation)) {
			height -= tilesize;
		} else if ("right".equals(orientation)) {
			width -= tilesize;
		}	else if ("left".equals(orientation)) {
			x += tilesize;
			width -= tilesize;
		}	else /* if ("null".equals(orientation)) */ {
			// Dock の方向がわからないので、どちらにあってもいいようにする
			x += tilesize;
			width -= 2 * tilesize;
		}

		Rectangle r = new Rectangle(x, y, width, height);
		return r;
	}

	private static String getDockOrientation() {
		CFTypeRef orientationRef =
			carbon.CFPreferencesCopyValue(
				kOrientation, kDock, Carbon.kCurrentUser, Carbon.kAnyHost);

		// CFPreferencesCopyValue が null を返す環境がある
		if (orientationRef == null) {
			return "null";
		}

		final int bufsize = 64;
		Memory buf = new Memory(64);
		carbon.CFStringGetCString(
			orientationRef, buf, bufsize, carbon.CFStringGetSystemEncoding());
		carbon.CFRelease(orientationRef);
		String ret = buf.getString(0, "UTF-8");
		buf.clear();
		return ret;
	}

	private static int getDockTileSize() {
	/**
   * 由于没有高效的方法来监控 Dock 的高度，
   * 这里暂时返回一个大于 Dock 最大尺寸的常量。
   *
   * 使用 CFPreferencesCopyValue 得到的值与 AppleScript 得到的值不同，
   * AppleScript 得到的值更为准确。
   *
   * 如果通过获取 pid 并使用 Accessibility API，可以获得正确的值，
   * 但如果执行 killall Dock，会导致 SEGV。
   * 为避免 SEGV，需要每次都重新获取 pid，但除了遍历进程列表外没有其他方法。
   * 考虑到调用频率，不希望使用 AppleScript。
   * 该权衡方案后续再考虑。
   */
		return 100;
	}

	private static void refreshDockState() {
		carbon.CFPreferencesAppSynchronize(kDock);
	}

	private static long getCurrentPID() {
		return currentPID;
	}

	private static void setCurrentPID(long newPID) {
		if (newPID != myPID) {
			currentPID = newPID;
			getTouchedProcesses().add(newPID);
		}
	}

	private static HashSet<Long> getTouchedProcesses() {
		return touchedProcesses;
	}

  private void updateFrontmostWindow() {
	final Rectangle
			frontmostWindowRect = getFrontmostAppRect(),
			windowVisibleArea = getWindowVisibleArea();

	frontmostWindow.setVisible(
	  (frontmostWindowRect != null)
	  && frontmostWindowRect.intersects(windowVisibleArea)
			&& !frontmostWindowRect.contains(windowVisibleArea) // デスクトップを除外
			);
	frontmostWindow.set(
	  frontmostWindowRect == null ? new Rectangle(-1, -1, 0, 0) : frontmostWindowRect);
  }

	private static void updateFrontmostApp() {
		long newPID = getFrontmostAppsPID();
		setCurrentPID(newPID);
	}

	@Override
	public void tick() {
		super.tick();
		MacEnvironment.updateFrontmostApp();
	this.updateFrontmostWindow();
	}

	@Override
	public void moveActiveIE(final Point point) {
		/**
			前述のとおり、完全に画面外へ移動しようとすると押し返されるため、
			そのような位置の指定に対しては可能なかぎりの移動に切り替える。
		 */
		final Rectangle
			visibleRect = getWindowVisibleArea(),
			windowRect  = getFrontmostAppRect();

		final double
			minX = visibleRect.getMinX() - windowRect.getWidth(), // 左方向の折り返し座標
			maxX = visibleRect.getMaxX(),													// 右方向の折り返し座標
			minY = visibleRect.getMinY(),													// 上方向の折り返し座標
																														// (メニューバーより
																														//  上へは移動できない)
			maxY = visibleRect.getMaxY();													// 下方向の折り返し座標

		double
			pX   = point.getX(),
			pY   = point.getY();

		// X方向の折り返し
		pX = betweenOrLimit(pX, minX, maxX);

		// Y方向の折り返し
		pY = betweenOrLimit(pY, minY, maxY);

		point.setLocation(pX, pY);
		moveFrontmostWindow(point);
	}

	@Override
	public void restoreIE() {
		final Rectangle visibleRect = getWindowVisibleArea();
		restoreWindowsNotIn(visibleRect);
		getTouchedProcesses().clear();
	}

	@Override
	public Area getWorkArea() {
		return getScreen();
	}

	@Override
	public Area getActiveIE() {
		return MacEnvironment.activeIE;
	}
		
	@Override
	public String getActiveIETitle( )
	{
		return null;
	}

	@Override
	public void refreshCache()
	{
	}

	@Override
	public void dispose( )
	{
	}
}
