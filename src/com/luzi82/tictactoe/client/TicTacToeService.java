package com.luzi82.tictactoe.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.luzi82.tictactoe.shared.XY;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("tictactoe")
public interface TicTacToeService extends RemoteService {
	XY move(int side,int[][] board);
}
