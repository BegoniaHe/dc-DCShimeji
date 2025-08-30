package com.group_finity.mascot.win.jna;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 * Original Author: Yuki Yamada of Group Finity
 * (<a href="http://www.group-finity.com/Shimeji/">...</a>)
 * Currently developed by Shimeji-ee Group.
 */

public class BLENDFUNCTION extends Structure {
	public static final byte AC_SRC_OVER = 0;
	public static final byte AC_SRC_ALPHA = 1;

	public byte BlendOp;
	public byte BlendFlags;
	public byte SourceConstantAlpha;
	public byte AlphaFormat;

	@Override
	protected List<String> getFieldOrder() {
		return Arrays.asList("BlendOp", "BlendFlags", "SourceConstantAlpha", "AlphaFormat");
	}
}
