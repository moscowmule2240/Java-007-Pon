/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 倉庫のマップを操作します。
 * 
 * @author moscowmule2240
 */
public class Soko {

	/**
	 * マップ。
	 */
	private final SokoMap sokoMap;

	/**
	 * コンストラクター。
	 * 
	 * @param filePath
	 *            ファイルパス
	 * @throws FileNotFoundException
	 *             ファイルが存在しない場合
	 * @throws IOException
	 *             ファイルが開けなかった場合
	 */
	public Soko(String filePath) throws FileNotFoundException, IOException {
		this.sokoMap = new SokoMap(filePath);
	}

	/**
	 * マップを出力します。
	 * 
	 * @return 出力マップ
	 */
	public String displayMap() {
		return this.sokoMap.display();
	}

	/**
	 * やり直しを行います。
	 * 
	 * @return やり直し可能かどうか
	 */
	public boolean Undo() {
		return this.sokoMap.undo();
	}

	/**
	 * 移動します。
	 * 
	 * @param input
	 *            移動方向
	 * @return 移動出来たかどうか
	 */
	public boolean move(char input) {
		Position position;

		// 上->w, 左->a, 下->s, 右->d
		switch (input) {
		case 'w':
			position = new Position(0, -1);
			break;
		case 'a':
			position = new Position(-1, 0);
			break;
		case 's':
			position = new Position(0, 1);
			break;
		case 'd':
			position = new Position(1, 0);
			break;
		default:
			return false;
		}

		return this.sokoMap.move(position);
	}

	/**
	 * クリアしたかどうか判定します。
	 * 
	 * @return クリア判定
	 */
	public boolean isClear() {
		return this.sokoMap.isClear();
	}
}
