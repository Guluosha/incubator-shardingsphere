/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.core.parsing.antlr.extractor.statement.handler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.ExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.handler.result.SQLTokenExtractResult;
import io.shardingsphere.core.parsing.antlr.extractor.statement.util.ASTUtils;
import io.shardingsphere.core.parsing.lexer.token.Symbol;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.parsing.parser.token.TableToken;

/**
 * Multiple table names extract handler.
 * 
 * @author duhongjun
 */
public final class TableNamesExtractHandler extends TableNameExtractHandler implements ASTExtractHandler1 {
    
    @Override
    public void extract(final ParserRuleContext ancestorNode, final SQLStatement statement) {
        for (ParseTree each : ASTUtils.getAllDescendantNodes(ancestorNode, RuleName.TABLE_NAME)) {
            super.extract((ParserRuleContext) each, statement);
        }
    }
    
    @Override
    public ExtractResult extract(ParserRuleContext ancestorNode) {
        SQLTokenExtractResult extractResult = new SQLTokenExtractResult();
        for (ParserRuleContext each : ASTUtils.getAllDescendantNodes(ancestorNode, RuleName.TABLE_NAME)) {
            String tableText = each.getText();
            int dotPosition = tableText.contains(Symbol.DOT.getLiterals()) ? tableText.lastIndexOf(Symbol.DOT.getLiterals()) : 0;
            extractResult.getSqlTokens().add(new TableToken(each.getStart().getStartIndex(), dotPosition, tableText));
        }
        return extractResult;
    }
}
