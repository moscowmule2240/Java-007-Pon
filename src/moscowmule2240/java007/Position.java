/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 位置を表します。
 * 
 * @author moscowmule2240
 */
public class Position {

	/**
	 * x座標。
	 */
	private int x;

	/**
	 * y座標。
	 */
	private int y;

	/**
	 * コンストラクター。
	 * 
	 * @param x
	 *            座標
	 * @param y
	 *            座標
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 加算した結果を返します。
	 * 
	 * @param position
	 *            加算する値
	 * @return 加算した結果
	 */
	public Position add(Position position) {
		int tempX = this.x + position.getX();
		int tempY = this.y + position.getY();
		return new Position(tempX, tempY);
	}

	/**
	 * 減算した結果を返します。
	 * 
	 * @param position
	 *            減算する値
	 * @return 減算した結果
	 */
	public Position subtract(Position position) {
		int tempX = this.x - position.getX();
		int tempY = this.y - position.getY();
		return new Position(tempX, tempY);
	}

	/**
	 * x座標を返します。
	 * 
	 * @return x x座標
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * y座標を返します。
	 * 
	 * @return y y座標
	 */
	public int getY() {
		return this.y;
	}
}
