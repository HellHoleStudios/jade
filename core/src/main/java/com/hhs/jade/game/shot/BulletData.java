package com.hhs.jade.game.shot;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hhs.jade.util.Collision;
import com.hhs.jade.util.Collision.CollisionData;

public class BulletData {

	public int id;
	public String name;
	public String render;
	public float originX, originY;
	public BulletTexture texture;
	public TextureRegion delayTexture;
	public Color delayColor;
	public float spinVelocity;
	public CollisionData collision;
	public float rotation;

	public BulletData(ShotSheet parent, RawShotSheet.RawBulletData raw) {
		this.id = raw.id;
		this.name = raw.name;

		if (raw.render == null) {
			this.render = "ALPHA";
		} else {
			this.render = raw.render;
		}

		if (raw.spinVelocity == null) {
			this.spinVelocity = 0;
		} else {
			this.spinVelocity = raw.spinVelocity;
		}

		if (raw.rotation == null) {
			this.rotation = 0;
		} else {
			this.rotation = raw.rotation;
		}

		if (raw.frames == null) {
			this.texture = new BulletTexture(parent.atlas, name);
		} else {
			this.texture = new BulletTexture(parent.atlas, name, raw.frames);
		}

		if ("Circle".equals(raw.collisionMethod)) {
			this.collision = new Collision.Circle(raw.collisionData[0]);
		} else if ("Rectangle".equals(raw.collisionMethod)) {
			this.collision = new Collision.Rectangle(raw.collisionData[0], raw.collisionData[1]);
		} else {
			this.collision = new Collision.Circle(raw.collisionData[0]);
		}

		if (raw.originX == null) {
			this.originX = texture.getMaxWidth() / 2;
		} else {
			this.originX = raw.originX;
		}

		if (raw.originY == null) {
			this.originY = texture.getMaxHeight() / 2;
		} else {
			this.originY = raw.originY;
		}

		this.delayColor = Color.valueOf(raw.delayColor);
		this.delayTexture = parent.atlas.findRegion(raw.delaySrc);
	}
}