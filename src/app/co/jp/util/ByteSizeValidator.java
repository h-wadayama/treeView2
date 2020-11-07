/*
 * 処理名     ：独自Validator
 * クラス名   ：ByteSizeValidator
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
package app.co.jp.util;

import java.io.UnsupportedEncodingException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.co.jp.util.IF.ByteSize;

/*
 * 処理名     ：バイト数チェック
 * メソッド名 ：ByteSizeValidator
 * 作成者     ： H.Wadayama
 * 作成日     ：2019-05-01
 */
public class ByteSizeValidator implements ConstraintValidator<ByteSize, String>{

	private String encoding;
	private int size;

	@Override
	public void initialize(ByteSize byteSize) {
		    encoding = byteSize.encoding();
		    size = byteSize.size();
	}

	  @Override
	  public boolean isValid(String value, ConstraintValidatorContext context) {
	    if (value == null) {
	      return true;
	    }

	    return isByteSizeValid(value);
	  }

	  /**
	   * 引数で指定した文字列のバイトサイズを検証します。
	   *
	   * @param value 検証する文字列
	   * @return trueの場合、指定した許容サイズ以下
	   */
	  private boolean isByteSizeValid(String value) {

		    try {
		      byte[] bytes = value.getBytes(encoding);

		      if (bytes.length > size) {
		        //大きい
		        return false;
		      }
		    } catch (UnsupportedEncodingException e) {
		      return false;
		    }

		    return true;
		  }

}
