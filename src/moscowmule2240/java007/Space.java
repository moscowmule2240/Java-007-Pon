/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 空白を表します。
 * 
 * @author moscowmule2240
 */
public class Space extends AbstractMoveTrue {

	/**
	 * コンストラクター。
	 * 
	 * @param sokoMap
	 *            マップ
	 */
	public Space(SokoMap sokoMap) {
		super(sokoMap);
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.AbstractMoveTrue#isMoveInOnly()
	 */
	@Override
	protected boolean isMoveInOnly() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#getSymbol()
	 */
	@Override
	public String getSymbol() {
		return " ";
	}
}
