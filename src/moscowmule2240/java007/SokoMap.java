/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * マップを管理します。
 * 
 * @author moscowmule2240
 */
public class SokoMap {

	/**
	 * マップ。
	 */
	private final List<List<Symbol>> maps;

	/**
	 * プレイヤーの位置。
	 */
	private Position playerPosition;

	/**
	 * 操作履歴。
	 */
	private Deque<Map<Position, Symbol>> undoDeque;

	/**
	 * コンストラクター。
	 * 
	 * @param filePath
	 *            ファイルパス。
	 * @throws FileNotFoundException
	 *             指定されたファイルパスが存在しない場合
	 * @throws IOException
	 *             指定されたファイルパスのファイルが読み込めなかった場合
	 */
	public SokoMap(String filePath) throws FileNotFoundException, IOException {
		this.undoDeque = new LinkedList<>();
		this.maps = this.getMap(filePath);
	}

	/**
	 * マップを解析します。
	 * 
	 * @param filePath
	 *            ファイルパス
	 * @return 解析結果
	 * @throws FileNotFoundException
	 *             指定されたファイルパスが存在しない場合
	 * @throws IOException
	 *             指定されたファイルパスのファイルが読み込めなかった場合
	 */
	private List<List<Symbol>> getMap(String filePath) throws FileNotFoundException, IOException {
		// 荷物
		List<Position> luggageList = new ArrayList<>();

		// 目的地
		List<Position> destinationList = new ArrayList<>();

		// マップ内容
		List<List<Symbol>> map = new ArrayList<>();

		// ファイルからマップ読み込み
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		String line;
		while ((line = reader.readLine()) != null) {

			// 空行
			if (line.length() == 0) {
				continue;
			}

			// コメント
			if (line.charAt(0) == ';') {
				continue;
			}

			// １行
			List<Symbol> list = new ArrayList<>();
			for (int i = 0; i < line.length(); i++) {
				// シンボル取得
				Symbol symbol = this.getSymbol(String.valueOf(line.charAt(i)));
				if (symbol == null) {
					throw new IllegalArgumentException("マップに使用できる文字は「p#.o 」です。");
				}

				Position position = new Position(i, map.size());
				if (symbol instanceof Player) {
					if (this.playerPosition != null) {
						throw new IllegalArgumentException("プレイヤーが複数います。");
					}
					this.playerPosition = position;
				} else if (symbol instanceof Luggage) {
					luggageList.add(position);
				} else if (symbol instanceof Destination) {
					destinationList.add(position);
				}
				list.add(symbol);
			}
			map.add(list);
		}

		if (this.playerPosition == null) {
			throw new IllegalArgumentException("プレイヤーがいません。");
		}

		if (luggageList.size() != destinationList.size()) {
			throw new IllegalArgumentException("荷物と目的地の数が一致していません。");
		}

		return map;
	}

	/**
	 * 現在のマップを表示します。
	 * 
	 * @return 現在のマップ
	 */
	public String display() {
		StringBuilder builder = new StringBuilder();
		for (List<Symbol> line : this.maps) {
			for (Symbol symbol : line) {
				builder.append(symbol.getSymbol());
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * 移動を行います。
	 * 
	 * @param increasePosition
	 *            移動先
	 * @return 移動出来たかどうか
	 */
	public boolean move(Position increasePosition) {
		// 移動先のシンボルを取得します。
		Position nextPosition = this.playerPosition.add(increasePosition);
		Symbol nextSymbol = this.getSymbol(nextPosition);

		// 移動先のシンボルに移動可能か判定します。
		if (nextSymbol.isMove(nextPosition, increasePosition)) {
			// 移動可能な場合、操作履歴を追加し移動します。
			Map<Position, Symbol> map = new HashMap<>();
			this.undoDeque.addLast(map);
			nextSymbol.move(nextPosition, increasePosition);

			// 移動後、プレイヤーがの存在していたマスを元のシンボルに戻します。
			Symbol player = this.getSymbol(this.playerPosition);
			if (player instanceof PlayerOnDestination) {
				this.setSymbol(this.playerPosition, this.getSymbol("."));
			} else {
				this.setSymbol(this.playerPosition, this.getSymbol(" "));
			}
			this.undoDeque.getLast().put(this.playerPosition, player);
			this.playerPosition = nextPosition;
			return true;
		}
		return false;
	}

	/**
	 * クリア判定を行います。
	 * 
	 * @return クリア判定
	 */
	public boolean isClear() {
		// 目的地が存在しない場合、クリアとみなす。
		for (List<Symbol> symbols : this.maps) {
			for (Symbol symbol : symbols) {
				if (symbol instanceof Destination) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 指定されたキーを表すシンボルを生成します。
	 * 
	 * @param key
	 *            キー
	 * @return 生成したシンボル
	 */
	public Symbol getSymbol(String key) {
		switch (key) {
		case "p":
			return new Player();
		case "P":
			return new PlayerOnDestination();
		case "#":
			return new Wall();
		case ".":
			return new Destination(this);
		case "o":
			return new Luggage(this);
		case "O":
			return new LuggageOnDestination(this);
		case " ":
			return new Space(this);
		case "null":
			return new Empty();
		default:
			throw new IllegalArgumentException("入力された値に誤りがあります。");
		}
	}

	/**
	 * 指定した座標のシンボルを取得します。
	 * 
	 * @param position
	 *            座標
	 * @return シンボル
	 */
	public Symbol getSymbol(Position position) {
		if (((this.maps.size() - 1) < position.getY()) || ((this.maps.get(position.getY()).size() - 1) < position.getX())) {
			return this.getSymbol("null");
		}
		return this.maps.get(position.getY()).get(position.getX());
	}

	/**
	 * 指定した座標にシンボルを設定します。
	 * 
	 * @param position
	 *            座標
	 * @param symbol
	 *            シンボル
	 */
	public void setSymbol(Position position, Symbol symbol) {
		this.maps.get(position.getY()).set(position.getX(), symbol);
	}

	/**
	 * やり直しリストを取得します。
	 * 
	 * @return やり直しリスト
	 */
	public Deque<Map<Position, Symbol>> getUndoList() {
		return this.undoDeque;
	}

	/**
	 * やり直しを行います。
	 * 
	 * @return やり直し結果
	 */
	public boolean undo() {
		// リストが空の場合
		if (this.undoDeque.isEmpty()) {
			return false;
		}

		// セット単位にやり直し操作を行います。
		Map<Position, Symbol> undo = this.undoDeque.removeLast();
		for (Entry<Position, Symbol> item : undo.entrySet()) {
			this.setSymbol(item.getKey(), item.getValue());
			if ((item.getValue() instanceof Player) || (item.getValue() instanceof PlayerOnDestination)) {
				this.playerPosition = item.getKey();
			}
		}
		return true;
	}
}
