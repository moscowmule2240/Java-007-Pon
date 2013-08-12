/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 荷物を表します。
 * 
 * @author moscowmule2240
 */
public class Luggage extends AbstractMoveTrue {

	/**
	 * コンストラクター。
	 * 
	 * @param sokoMap
	 *            マップ
	 */
	public Luggage(SokoMap sokoMap) {
		super(sokoMap);
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.AbstractMoveTrue#isMoveInOnly()
	 */
	@Override
	protected boolean isMoveInOnly() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#getSymbol()
	 */
	@Override
	public String getSymbol() {
		return "o";
	}
}
