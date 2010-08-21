package com.luzi82.tictactoe.shared;

import java.io.Serializable;

public class XY implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7804925008050203360L;

	public XY() {
		this(0, 0);
	}

	public XY(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int x, y;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof XY) {
			XY o = (XY) obj;
			return (o.x == x) && (o.y == y);
		} else {
			return false;
		}
	}

}
