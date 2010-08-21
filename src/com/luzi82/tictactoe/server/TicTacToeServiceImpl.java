package com.luzi82.tictactoe.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.luzi82.tictactoe.client.TicTacToeService;
import com.luzi82.tictactoe.shared.XY;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TicTacToeServiceImpl extends RemoteServiceServlet implements
		TicTacToeService {

	TicTacToeAI ai = new TicTacToeAI();

	@Override
	public XY move(int side, int[][] board) {
		int[][] thinker = new int[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				int b = board[i][j];
				thinker[i][j] = (b == side) ? 1 : (b == 0) ? 0 : -1;
			}
		}

		// ArrayList<int[]> ret = listMove(thinker);
		//
		// int retSize = ret.size();
		// Random ran = new Random();
		// int retIdx = ran.nextInt(retSize);
		//
		// return ret.get(retIdx);

		return ai.move(thinker);
	}

}
