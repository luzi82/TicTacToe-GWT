package com.luzi82.tictactoe.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.luzi82.tictactoe.shared.XY;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface TicTacToeServiceAsync {
	void move(int side, int[][] board, AsyncCallback<XY> callback);
}
