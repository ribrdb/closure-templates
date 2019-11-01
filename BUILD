exports_files(
    ["tsconfig.json"],
    visibility = ["//visibility:public"],
)

load("@npm_bazel_typescript//:index.bzl", "ts_library")
load("@build_bazel_rules_nodejs//:defs.bzl", "nodejs_binary")
load(":es6_consumer.bzl", "es6_consumer", "exclude_ts_output")

nodejs_binary(
    name = "tsc_wrapped_bin",
    data = [
        "@npm//@bazel/typescript",
        "@npm//protobufjs",
        "@npm//source-map-support",
        "@npm//tsickle",
        "@npm//tsutils",
        "@npm//typescript",
    ],
    entry_point = "@npm//:node_modules/@bazel/typescript/internal/tsc_wrapped/tsc_wrapped.js",
)

genrule(
  name="copy_idom",
  srcs=[
    "@npm//:node_modules/incremental-dom/index.ts",
        "@npm//:node_modules/incremental-dom/src/assertions.ts",
        "@npm//:node_modules/incremental-dom/src/attributes.ts",
        "@npm//:node_modules/incremental-dom/src/changes.ts",
        "@npm//:node_modules/incremental-dom/src/context.ts",
        "@npm//:node_modules/incremental-dom/src/core.ts",
        "@npm//:node_modules/incremental-dom/src/diff.ts",
        "@npm//:node_modules/incremental-dom/src/dom_util.ts",
        "@npm//:node_modules/incremental-dom/src/global.ts",
        "@npm//:node_modules/incremental-dom/src/node_data.ts",
        "@npm//:node_modules/incremental-dom/src/nodes.ts",
        "@npm//:node_modules/incremental-dom/src/notifications.ts",
        "@npm//:node_modules/incremental-dom/src/symbols.ts",
        "@npm//:node_modules/incremental-dom/src/types.ts",
        "@npm//:node_modules/incremental-dom/src/util.ts",
        "@npm//:node_modules/incremental-dom/src/virtual_elements.ts"
  ],
  outs=[
    "incremental-dom/index.ts",
        "incremental-dom/src/assertions.ts",
        "incremental-dom/src/attributes.ts",
        "incremental-dom/src/changes.ts",
        "incremental-dom/src/context.ts",
        "incremental-dom/src/core.ts",
        "incremental-dom/src/debug.ts",
        "incremental-dom/src/diff.ts",
        "incremental-dom/src/dom_util.ts",
        "incremental-dom/src/global.ts",
        "incremental-dom/src/node_data.ts",
        "incremental-dom/src/nodes.ts",
        "incremental-dom/src/notifications.ts",
        "incremental-dom/src/symbols.ts",
        "incremental-dom/src/types.ts",
        "incremental-dom/src/util.ts",
        "incremental-dom/src/virtual_elements.ts"
  ],cmd="""
mkdir -p $(RULEDIR)/incremental-dom/src
OUTDIR=$(RULEDIR)/incremental-dom
cp $(location @npm//:node_modules/incremental-dom/index.ts) $$OUTDIR
cp $(location @npm//:node_modules/incremental-dom/src/assertions.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/attributes.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/changes.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/context.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/core.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/diff.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/dom_util.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/global.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/node_data.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/nodes.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/notifications.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/symbols.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/types.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/util.ts) $$OUTDIR/src/
cp $(location @npm//:node_modules/incremental-dom/src/virtual_elements.ts) $$OUTDIR/src/
echo 'export const DEBUG = false;' >$$OUTDIR/src/debug.ts
"""
)

ts_library(
    name = "incremental_dom",
    generate_externs = False,
    srcs = [
      ":copy_idom"
    ],
    compiler = ":tsc_wrapped_bin",
)

ts_library(
    name = "closure_templates_ts",
    srcs = glob(["javascript/*.ts"]),
    generate_externs = False,
    compiler = ":tsc_wrapped_bin",
    deps = [":incremental_dom"],
)

es6_consumer(
    name = "ts_output",
    deps = [
        ":closure_templates_ts",
        ":incremental_dom",
    ],
)

filegroup(
    name = "closure_templates",
    srcs = exclude_ts_output(
        glob(["src/*.js"]),
        glob(["src/*.ts"]),
    ) + [":ts_output"],
    visibility = ["//visibility:public"],
)
