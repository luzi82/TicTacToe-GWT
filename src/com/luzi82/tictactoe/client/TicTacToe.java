package com.luzi82.tictactoe.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.luzi82.tictactoe.shared.XY;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TicTacToe implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	public static int TYPE_AI_FIRST = 0;
	public static int TYPE_PLAYER_FIRST = 1;
	public static int TYPE_PVP = 2;

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final TicTacToeServiceAsync tictactoeService = GWT
			.create(TicTacToeService.class);

	int[][] cellValue = new int[3][3];
	RootPanel[][] cellPanel = new RootPanel[3][3];
	PushButton[][] cellButton = new PushButton[3][3];
	Image[][] cellImg = new Image[3][3];
	final String X = "res/X.png";
	final String O = "res/O.png";
	final String _ = "res/_.png";

	int type;
	int turn;
	int win;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cellPanel[i][j] = RootPanel.get("cell" + i + j);
				cellImg[i][j] = new Image(_);
				cellButton[i][j] = new PushButton(cellImg[i][j], CLICKHANDLER);
				cellPanel[i][j].add(cellButton[i][j]);
			}
		}

		RootPanel controlPanel = RootPanel.get("control");
		HorizontalPanel controlPanelH = new HorizontalPanel();

		PushButton pvpBtn = new PushButton("PVP", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				reset(TYPE_PVP);
			}
		});
		controlPanelH.add(pvpBtn);

		PushButton aiFirst = new PushButton("AI First", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				reset(TYPE_AI_FIRST);
			}
		});
		controlPanelH.add(aiFirst);

		PushButton playerFirst = new PushButton("Player First",
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						reset(TYPE_PLAYER_FIRST);
					}
				});
		controlPanelH.add(playerFirst);
		controlPanel.add(controlPanelH);

		reset(TYPE_PVP);

		// final Button sendButton = new Button("Send");
		// final TextBox nameField = new TextBox();
		// nameField.setText("GWT User");
		// final Label errorLabel = new Label();
		//
		// // We can add style names to widgets
		// sendButton.addStyleName("sendButton");
		//
		// // Add the nameField and sendButton to the RootPanel
		// // Use RootPanel.get() to get the entire body element
		// RootPanel.get("nameFieldContainer").add(nameField);
		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);
		//
		// // Focus the cursor on the name field when the app loads
		// nameField.setFocus(true);
		// nameField.selectAll();
		//
		// // Create the popup dialog box
		// final DialogBox dialogBox = new DialogBox();
		// dialogBox.setText("Remote Procedure Call");
		// dialogBox.setAnimationEnabled(true);
		// final Button closeButton = new Button("Close");
		// // We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		// VerticalPanel dialogVPanel = new VerticalPanel();
		// dialogVPanel.addStyleName("dialogVPanel");
		// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		// dialogVPanel.add(textToServerLabel);
		// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		// dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		// dialogVPanel.add(closeButton);
		// dialogBox.setWidget(dialogVPanel);
		//
		// // Add a handler to close the DialogBox
		// closeButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// dialogBox.hide();
		// sendButton.setEnabled(true);
		// sendButton.setFocus(true);
		// }
		// });
		//
		// // Create a handler for the sendButton and nameField
		// class MyHandler implements ClickHandler, KeyUpHandler {
		// /**
		// * Fired when the user clicks on the sendButton.
		// */
		// public void onClick(ClickEvent event) {
		// sendNameToServer();
		// }
		//
		// /**
		// * Fired when the user types in the nameField.
		// */
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		// sendNameToServer();
		// }
		// }
		//
		// /**
		// * Send the name from the nameField to the server and wait for a
		// response.
		// */
		// private void sendNameToServer() {
		// // First, we validate the input.
		// errorLabel.setText("");
		// String textToServer = nameField.getText();
		// if (!FieldVerifier.isValidName(textToServer)) {
		// errorLabel.setText("Please enter at least four characters");
		// return;
		// }
		//
		// // Then, we send the input to the server.
		// sendButton.setEnabled(false);
		// textToServerLabel.setText(textToServer);
		// serverResponseLabel.setText("");
		// greetingService.greetServer(textToServer,
		// new AsyncCallback<String>() {
		// public void onFailure(Throwable caught) {
		// // Show the RPC error message to the user
		// dialogBox
		// .setText("Remote Procedure Call - Failure");
		// serverResponseLabel
		// .addStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(SERVER_ERROR);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		//
		// public void onSuccess(String result) {
		// dialogBox.setText("Remote Procedure Call");
		// serverResponseLabel
		// .removeStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(result);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		// });
		// }
		// }
		//
		// // Add a handler to send the name to the server
		// MyHandler handler = new MyHandler();
		// sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);
	}

	void move(int x, int y, int side) {
		if (win != 0) {
			return;
		}
		if (cellValue[x][y] != 0) {
			return;
		}
		cellValue[x][y] = side;
		switch (side) {
		case 1:
			cellImg[x][y].setUrl(X);
			break;
		case 2:
			cellImg[x][y].setUrl(O);
			break;
		}
		win = winner();
		++turn;
		if (isAiMove()) {
			aiMove();
		}
	}

	void reset(int type) {
		this.type = type;
		this.turn = 0;
		this.win = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cellValue[i][j] = 0;
				cellImg[i][j].setUrl(_);
			}
		}
		if (isAiMove()) {
			aiMove();
		}
	}

	boolean isAiMove() {
		if (win != 0) {
			return false;
		} else if ((type == TYPE_AI_FIRST) && ((turn & 1) == 0)) {
			return true;
		} else if ((type == TYPE_PLAYER_FIRST) && ((turn & 1) != 0)) {
			return true;
		} else {
			return false;
		}
	}

	void aiMove() {
		tictactoeService.move(turnToSide(turn), cellValue,
				new AsyncCallback<XY>() {
					@Override
					public void onSuccess(XY result) {
						move(result.x, result.y, turnToSide(turn));
					}
					@Override
					public void onFailure(Throwable caught) {
					}
				});
	}

	int winner() {
		int t;
		for (int i = 0; i < 3; ++i) {
			t = winTest(i, 0, 0, 1);
			if (t != 0) {
				return t;
			}
			t = winTest(0, i, 1, 0);
			if (t != 0) {
				return t;
			}
		}
		t = winTest(0, 0, 1, 1);
		if (t != 0) {
			return t;
		}
		t = winTest(0, 2, 1, -1);
		if (t != 0) {
			return t;
		}
		return 0;
	}

	int winTest(int x, int y, int dx, int dy) {
		if (cellValue[x][y] != cellValue[x + dx][y + dy]) {
			return 0;
		}
		if (cellValue[x][y] != cellValue[x + dx + dx][y + dy + dy]) {
			return 0;
		}
		return cellValue[x][y];
	}

	final ClickHandler CLICKHANDLER = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			if (isAiMove())
				return;
			PushButton button = (PushButton) event.getSource();
			int myI = -1, myJ = -1;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					if (button == cellButton[i][j]) {
						myI = i;
						myJ = j;
					}
				}
			}
			move(myI, myJ, turnToSide(turn));
		}
	};

	int turnToSide(int turn) {
		return ((turn & 1) == 0) ? 1 : 2;
	}

}
