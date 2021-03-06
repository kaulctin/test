package cn.aulati.test.T001;

import cn.aulati.test.ITest;
import cn.aulati.test.model.Beetle;
import cn.aulati.test.model.RoundGlyph;
import cn.aulati.test.model.SonClass;

/**
 * @author Aulati
 *
 */
public class ExtendsAndImplements implements ITest {

	/**
	 * start method of this test class.
	 * 
	 * @see cn.aulati.test.ITest#runTest()
	 */
	@Override
	public void runTest() {
		test03();
	}

	/**
	 * What's the sequence of static statement, non-static statement, and statement inside constructor.
	 */
	private void test01() {
		new SonClass();
	}
	
	private void test02() {
		new Beetle();
	}
	
	private void test03() {
		new RoundGlyph(5);
	}
}
