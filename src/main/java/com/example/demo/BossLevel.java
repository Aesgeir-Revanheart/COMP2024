package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class BossLevel extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;
	private Timeline shieldTimeline;

	public BossLevel(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			winGame();
			stopShieldTimeline(); // Stop shield timeline when boss is defeated
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			activateBossShieldPeriodically(); // Activate shield logic
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

	private void activateBossShieldPeriodically() {
		shieldTimeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
			levelView.showShield(); // Show the shield
			new Timeline(new KeyFrame(Duration.seconds(2), hideEvent -> {
				levelView.hideShield(); // Hide the shield after 2 seconds
			})).play();
		}));
		shieldTimeline.setCycleCount(Timeline.INDEFINITE);
		shieldTimeline.play();
	}

	private void stopShieldTimeline() {
		if (shieldTimeline != null) {
			shieldTimeline.stop();
		}
	}
}
