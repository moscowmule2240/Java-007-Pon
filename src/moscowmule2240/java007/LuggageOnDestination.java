/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 目的地上の荷物を表します。
 * 
 * @author moscowmule2240
 */
public class LuggageOnDestination extends Luggage {

	/**
	 * コンストラクター。
	 * 
	 * @param sokoMap
	 *            マップ
	 */
	public LuggageOnDestination(SokoMap sokoMap) {
		super(sokoMap);
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.AbstractMoveTrue#convertSymbol(moscowmule2240.java007.Symbol)
	 */
	@Override
	protected Symbol convertSymbol(Symbol symbol) {
		if (symbol instanceof Player) {
			return this.sokoMap.getSymbol("P");
		} else if (symbol instanceof Luggage) {
			return this.sokoMap.getSymbol("O");
		}
		return symbol;
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Luggage#getSymbol()
	 */
	@Override
	public String getSymbol() {
		return "O";
	}
}
