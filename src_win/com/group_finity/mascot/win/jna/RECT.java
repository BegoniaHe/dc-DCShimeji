package com.group_finity.mascot.win.jna;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 * Original Author: Yuki Yamada of Group Finity
 * (<a href="http://www.group-finity.com/Shimeji/">...</a>)
 * Currently developed by Shimeji-ee Group.
 */

public class RECT extends Structure {

	public int left;
	public int top;
	public int right;
	public int bottom;

	public int Width() {
		return this.right - this.left;
	}

	public int Height() {
		return this.bottom - this.top;
	}

	public void OffsetRect(final int dx, final int dy) {
		this.left += dx;
		this.right += dx;
		this.top += dy;
		this.bottom += dy;
	}

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("left", "top", "right", "bottom");
	}
}
