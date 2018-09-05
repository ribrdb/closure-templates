/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.passes;

import com.google.template.soy.base.internal.IdGenerator;
import com.google.template.soy.error.ErrorReporter;
import com.google.template.soy.error.SoyErrorKind;
import com.google.template.soy.soytree.SoyFileNode;
import com.google.template.soy.soytree.TemplateNode;

/** Passes through file stateful templates to validate that they are stricthtml templates. */
final class StatefulTemplatePass extends CompilerFilePass {

  private static final SoyErrorKind REQUIRE_STRICTHTML =
      SoyErrorKind.of("Stateful templates cannot be of type stricthtml=\"false\".");

  private final ErrorReporter errorReporter;

  StatefulTemplatePass(ErrorReporter errorReporter) {
    this.errorReporter = errorReporter;
  }

  @Override
  public void run(SoyFileNode file, IdGenerator nodeIdGen) {
    for (TemplateNode template : file.getChildren()) {
      if (!template.isStatefulTemplate()) {
        continue;
      }

      if (!template.isStrictHtml()) {
        errorReporter.report(template.getSourceLocation(), REQUIRE_STRICTHTML);
      }
    }
  }
}