/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * シンボルの定義を行います。
 * 
 * @author moscowmule2240
 */
public interface Symbol {

	/**
	 * このシンボルに別のシンボルが入れるか判定します。
	 * 
	 * @param position
	 *            現在の位置
	 * @param increasePosition
	 *            移動距離
	 * @return 判定結果
	 */
	public abstract boolean isMove(Position position, Position increasePosition);

	/**
	 * このシンボルに別のシンボルが入ってきた時の処理を行います。
	 * 
	 * @param position
	 *            現在の位置
	 * @param increasePosition
	 *            移動距離
	 */
	public abstract void move(Position position, Position increasePosition);

	/**
	 * シンボルを返します。
	 * 
	 * @return シンボル
	 */
	public abstract String getSymbol();
}
