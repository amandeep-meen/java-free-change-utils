package org.annotation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ EnumTest.class, StudentTest.class, StudentColleagueTest.class })
public class TestSuite {

}
