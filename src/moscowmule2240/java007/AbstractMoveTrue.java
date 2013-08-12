/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 移動可能なシンボルを表します。
 * 
 * @author moscowmule2240
 */
public abstract class AbstractMoveTrue implements Symbol {

	/**
	 * マップ。
	 */
	protected final SokoMap sokoMap;

	/**
	 * 別のシンボルによってこのシンボルを移動させることができるか。
	 * 
	 * @return
	 */
	protected abstract boolean isMoveInOnly();

	/**
	 * コンストラクター。
	 * 
	 * @param sokoMap
	 *            マップ。
	 */
	public AbstractMoveTrue(SokoMap sokoMap) {
		this.sokoMap = sokoMap;
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#isMove(moscowmule2240.java007.Position, moscowmule2240.java007.Position)
	 */
	@Override
	public boolean isMove(Position position, Position increasePosition) {
		// このシンボルが移動しない場合
		if (this.isMoveInOnly()) {
			return true;
		}

		// このシンボルが移動する場合、次のマスへ移動可能か判定
		Position nextPosition = position.add(increasePosition);
		Symbol nextSymbol = this.sokoMap.getSymbol(nextPosition);
		return nextSymbol.isMove(nextPosition, increasePosition);
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#move(moscowmule2240.java007.Position, moscowmule2240.java007.Position)
	 */
	@Override
	public void move(Position position, Position increasePosition) {
		// やり直し用に追加
		this.sokoMap.getUndoList().getLast().put(position, this);

		// このシンボルが移動する場合、次のシンボルを移動
		if (!this.isMoveInOnly()) {
			Position nextPosition = position.add(increasePosition);
			Symbol nextSymbol = this.sokoMap.getSymbol(nextPosition);
			nextSymbol.move(nextPosition, increasePosition);
		}

		// 一つ前のマスのシンボルを取得
		Position previousPosition = position.subtract(increasePosition);
		Symbol previousSymbol = this.sokoMap.getSymbol(previousPosition);

		// 一つ前のマスのシンボルを現在のマスへ移動
		Symbol currentSymbol = this.convertBaseSymbol(previousSymbol);
		currentSymbol = this.convertSymbol(currentSymbol);
		this.sokoMap.setSymbol(position, currentSymbol);
	}

	/**
	 * シンボルを通常のシンボルへ変換します。
	 * 
	 * @param symbol
	 *            変換対象シンボル
	 * @return 変換後のシンボル
	 */
	private Symbol convertBaseSymbol(Symbol symbol) {
		if (symbol instanceof PlayerOnDestination) {
			return this.sokoMap.getSymbol("p");
		}
		if (symbol instanceof LuggageOnDestination) {
			return this.sokoMap.getSymbol("o");
		}
		return symbol;
	}

	/**
	 * 表記の変更が必要なマスの場合シンボルを変換します。
	 * 
	 * @param symbol
	 *            変換対象シンボル
	 * @return 変換後のシンボル
	 */
	protected Symbol convertSymbol(Symbol symbol) {
		return symbol;
	}
}
