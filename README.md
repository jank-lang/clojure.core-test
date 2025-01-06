# The clojure.core test suite
This is a set of tests for Clojure's core standard library. Its purpose is not
only to draw clear boundaries around Clojure JVM's behavior, but also to serve
as a compliance suite for other Clojure dialects.

Currently, this project is owned by jank, the native Clojure dialect. As we
build it up and prove jank's readiness, we also create value for the rest of the
Clojure community. As it currently stands, jank isn't able to run `clojure.test`
yet, so we're just focusing on building out the test cases for now.

## How to contribute
Anyone with Clojure knowledge can help out!

Check out the latest progress and the steps for helping out here: https://github.com/jank-lang/clojure.core-test/issues/1

## Running the tests
For a one-off run, you can use the following:

```bash
$ lein test
```

However, during development, you can use `test-refresh` to automatically re-run
the test suite whenever you save new changes.

```bash
$ lein test-refresh
```
## Running the clojurescript tests

First, make sure you haev node 23 or greater.

Install the node dependencies:

```bash
npm install
```

Compile the tests:

```bash
npx shadow-cljs compile test
```

Once tests are compiled they can be ran with:

```bash
node target/js/node-tests.js
```

### Automated test running during development

If you want to autorun the tests during development run the following:

```bash
npx shadow-cljs watch test
```
