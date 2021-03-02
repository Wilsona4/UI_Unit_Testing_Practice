package com.funcrib.expressouitestingpractice.ui.main

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    LoginActivityTest::class,
    DialogAndToastTest::class
)
class ActivityTestSuite