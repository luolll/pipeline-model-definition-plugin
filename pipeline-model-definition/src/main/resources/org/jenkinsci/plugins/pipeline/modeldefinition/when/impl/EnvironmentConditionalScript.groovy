/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package org.jenkinsci.plugins.pipeline.modeldefinition.when.impl

import groovy.json.StringEscapeUtils
import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
import org.jenkinsci.plugins.pipeline.modeldefinition.when.DeclarativeStageConditionalScript
import org.jenkinsci.plugins.workflow.cps.CpsScript


class EnvironmentConditionalScript extends DeclarativeStageConditionalScript<EnvironmentConditional> {
    public EnvironmentConditionalScript(CpsScript s, EnvironmentConditional c) {
        super(s, c)
    }

    @Override
    public boolean evaluate() {
        String n = StringEscapeUtils.unescapeJava((String)script.evaluate(Utils.prepareForEvalToString(describable.getName())))
        String v = StringEscapeUtils.unescapeJava((String)script.evaluate(Utils.prepareForEvalToString(describable.getValue())))
        return describable.environmentMatches(v, (String)script.getProperty("env").getProperty(n))
    }
}
