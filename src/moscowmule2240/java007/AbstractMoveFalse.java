/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

/**
 * 移動不可であるシンボルを表します。
 * 
 * @author moscowmule2240
 */
public abstract class AbstractMoveFalse implements Symbol {
	
	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#isMove(moscowmule2240.java007.Position, moscowmule2240.java007.Position)
	 */
	@Override
	public boolean isMove(Position position, Position increasePosition) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see moscowmule2240.java007.Symbol#move(moscowmule2240.java007.Position, moscowmule2240.java007.Position)
	 */
	@Override
	public void move(Position position, Position increasePosition) {
		throw new RuntimeException();
	}
}
