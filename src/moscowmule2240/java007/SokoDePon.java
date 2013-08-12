/**
 * 倉庫でポン
 */
package moscowmule2240.java007;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 倉庫でポン。
 * 
 * @author moscowmule2240
 */
public class SokoDePon {

	/**
	 * エントリーポイント。
	 * 
	 * @param args
	 *            マップのファイルパス
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("引数にはマップのファイルパスを指定してください。");
			return;
		}

		try {
			// マップ読み込み
			String filePath = args[0];
			Soko soko = new Soko(filePath);

			// タイトル表示
			System.out.println(SokoDePon.getTitle());
			System.out.println("Please Enter Key... Game Start");

			// 入力受付
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			reader.readLine();

			// クリアするまで繰り返し
			int count = 0;
			while (!soko.isClear()) {
				// 現在状態表示
				System.out.print(soko.displayMap());

				// 移動回数表示
				System.out.println("移動回数: " + count);

				// 入力内容表示
				System.out.println("移動: (上->w, 左->a, 下->s, 右->d) + Enter");
				System.out.println("戻す->u, リセット->@, 入力キャンセル->!を含める");

				// 入力受付
				String input = reader.readLine();

				// 未入力、または、！
				if (input.isEmpty() || input.contains("!")) {
					continue;
				}

				// 移動以外
				switch (input.charAt(0)) {
				case '@':
					soko = new Soko(filePath);
					count = 0;
					continue;
				case 'u':
					if (soko.Undo()) {
						count--;
					}
					continue;
				default:
				}

				// 移動
				if (soko.move(input.charAt(0))) {
					count++;
				}
			}

			// クリア結果表示
			System.out.print(soko.displayMap());

			// 移動回数表示
			System.out.println("移動回数: " + count);

			System.out.println("ゲームクリア！おめでとう！！");
			System.out.println("Please Enter Key... Game end");
			reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("引数にはマップのファイルパスを指定してください。");
		}
	}

	/**
	 * タイトルを返します。
	 * 
	 * @return タイトル
	 */
	private static String getTitle() {
		StringBuilder builder = new StringBuilder();
		builder.append("＿＿＿＿■■■＿＿＿＿＿＿＿＿＿＿＿■＿＿＿＿＿＿＿＿＿＿＿＿■■＿＿\n");
		builder.append("＿＿＿■＿＿＿■＿＿＿＿＿■■■■■■■■■■＿＿＿■■■■■＿■＿＿\n");
		builder.append("＿＿■＿■■■＿■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿＿■＿■＿■＿＿＿\n");
		builder.append("■■＿＿＿＿＿＿＿■■＿＿■■■■■■■■■■＿■■■■■■■■■■■\n");
		builder.append("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿＿＿＿■＿■＿■＿＿＿\n");
		builder.append("＿＿■＿＿＿＿＿■＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿\n");
		builder.append("＿＿■■■■■■■＿＿＿＿■＿■＿＿■＿＿■＿＿■■■■■■■■■■■\n");
		builder.append("＿＿■＿＿＿＿＿＿＿＿＿＿■＿■■■■■■■＿＿＿＿■＿＿■＿＿■＿＿\n");
		builder.append("＿■＿■■■■■■＿＿＿＿■＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿\n");
		builder.append("■＿＿■＿＿＿＿■＿＿＿■＿■■■■■■■■■＿＿＿■＿＿■＿＿■＿＿\n");
		builder.append("＿＿＿■■■■■■＿＿＿■＿＿＿＿＿■＿＿＿＿＿＿＿■■■■■■■＿＿\n");
		builder.append("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
		return builder.toString();
	}
}
