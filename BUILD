exports_files(
    ["tsconfig.json"],
    visibility = ["//visibility:public"],
)

load("@npm_bazel_typescript//:index.bzl", "ts_library")
load("@build_bazel_rules_nodejs//:defs.bzl", "nodejs_binary")

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

ts_library(
    name = "incremental_dom",
    srcs = [
        "@incremental_dom//:incremental_dom",
    ],
    compiler = ":tsc_wrapped_bin",
)

ts_library(
    name = "closure_templates_ts",
    srcs = glob(["javascript/*.ts"]),
    compiler = ":tsc_wrapped_bin",
    deps = [":incremental_dom"],
)

filegroup(
    name = "closure_templates",
    srcs = [
        "javascript/checks.js",
        "javascript/jspbconversions.js",
        "javascript/required_by_soy.js",
        "javascript/shim.js",
        "javascript/soy_requirements_onefile.js",
        "javascript/soydata_converters.js",
        "javascript/soyutils_map.js",
        "javascript/soyutils_newmaps.js",
        "javascript/soyutils_usegoog.js",
        "javascript/soyutils_velog.js",
        "javascript/types.js",
        ":closure_templates_ts",
        ":incremental_dom",
    ],
    output_group = "es6_sources",
    visibility = ["//visibility:public"],
)
