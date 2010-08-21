package com.luzi82.tictactoe.client;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.luzi82.tictactoe.server.TicTacToeAI;
import com.luzi82.tictactoe.shared.XY;

public class TicTacToeAITest extends TestCase {

	public void testBasic() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(1);
		b.add(2);
		assertTrue(a.equals(b));

		int[] c = { 1, 2 };
		int[] d = { 1, 2 };
		assertTrue(!c.equals(d));

		ArrayList<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
		e.add(a);
		assertTrue(e.contains(b));

		XY xy0 = new XY(1, 2);
		XY xy1 = new XY(1, 2);
		XY xy2 = new XY(2, 3);
		ArrayList<XY> xya0 = new ArrayList<XY>();
		ArrayList<XY> xya1 = new ArrayList<XY>();
		xya0.add(xy0);
		xya1.add(xy1);
		assertTrue(xya0.contains(xy1));
		assertTrue(!xya0.contains(xy2));
	}

	public void testMax() {
		TicTacToeAI ai = new TicTacToeAI();
		int ret;

		int[][] thinker0 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		ret = ai.max(thinker0);
		assertTrue(ret == 0);

		int[][] thinker1 = { { 1, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 } };
		ret = ai.max(thinker1);
		assertTrue(ret == 100);

		int[][] thinker2 = { { -1, -1, -1 }, { 0, 0, 0 }, { 0, 0, 0 } };
		ret = ai.max(thinker2);
		assertTrue(ret == -100);

		int[][] thinker3 = { { 1, 0, -1 }, { 1, 1, 0 }, { -1, 0, 0 } };
		ret = ai.max(thinker3);
		assertTrue(ret > 0);

		int[][] thinker4 = { { -1, 0, 1 }, { -1, -1, 0 }, { 1, 0, 0 } };
		ret = ai.max(thinker4);
		assertTrue(ret < 0);
	}

	public void testListMove() {
		TicTacToeAI ai = new TicTacToeAI();

		ArrayList<XY> ret;

		int[][] thinker0 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		ret = ai.listMove(thinker0);
		assertTrue(ret.size() == 9);
		assertTrue(ret.contains(new XY(0, 0)));
		assertTrue(ret.contains(new XY(0, 1)));
		assertTrue(ret.contains(new XY(0, 2)));
		assertTrue(ret.contains(new XY(1, 0)));
		assertTrue(ret.contains(new XY(1, 1)));
		assertTrue(ret.contains(new XY(1, 2)));
		assertTrue(ret.contains(new XY(2, 0)));
		assertTrue(ret.contains(new XY(2, 1)));
		assertTrue(ret.contains(new XY(2, 2)));

		int[][] thinker1 = { { -1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		ret = ai.listMove(thinker1);
		assertTrue(ret.size() == 1);
		assertTrue(ret.contains(new XY(1, 1)));
	}

}
