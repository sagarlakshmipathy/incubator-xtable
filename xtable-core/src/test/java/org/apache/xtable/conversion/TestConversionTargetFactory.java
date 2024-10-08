/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package org.apache.xtable.conversion;

import static org.apache.xtable.GenericTable.getTableName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.Test;

import org.apache.xtable.exception.NotSupportedException;
import org.apache.xtable.model.storage.TableFormat;
import org.apache.xtable.spi.sync.ConversionTarget;

public class TestConversionTargetFactory {

  @Test
  public void testConversionTargetFromNameForDELTA() {
    ConversionTarget tc =
        ConversionTargetFactory.getInstance().createConversionTargetForName(TableFormat.DELTA);
    assertNotNull(tc);
    TargetTable targetTable = getPerTableConfig(TableFormat.DELTA);
    Configuration conf = new Configuration();
    conf.set("spark.master", "local");
    tc.init(targetTable, conf);
    assertEquals(tc.getTableFormat(), TableFormat.DELTA);
  }

  @Test
  public void testConversionTargetFromNameForHUDI() {
    ConversionTarget tc =
        ConversionTargetFactory.getInstance().createConversionTargetForName(TableFormat.HUDI);
    assertNotNull(tc);
    TargetTable targetTable = getPerTableConfig(TableFormat.HUDI);
    Configuration conf = new Configuration();
    conf.setStrings("spark.master", "local");
    tc.init(targetTable, conf);
    assertEquals(tc.getTableFormat(), TableFormat.HUDI);
  }

  @Test
  public void testConversionTargetFromNameForICEBERG() {
    ConversionTarget tc =
        ConversionTargetFactory.getInstance().createConversionTargetForName(TableFormat.ICEBERG);
    assertNotNull(tc);
    TargetTable targetTable = getPerTableConfig(TableFormat.ICEBERG);
    Configuration conf = new Configuration();
    conf.setStrings("spark.master", "local");
    tc.init(targetTable, conf);
    assertEquals(tc.getTableFormat(), TableFormat.ICEBERG);
  }

  @Test
  public void testConversionTargetFromNameForUNKOWN() {
    NotSupportedException thrown =
        assertThrows(
            NotSupportedException.class,
            () -> ConversionTargetFactory.getInstance().createConversionTargetForName("UNKNOWN"),
            "NotSupportedException expected and operation succeeded inappropriately.");
    assertTrue(thrown.getMessage().contains("UNKNOWN"));
  }

  @Test
  public void testConversionTargetFromFormatType() {
    TargetTable targetTable = getPerTableConfig(TableFormat.DELTA);
    Configuration conf = new Configuration();
    conf.setStrings("spark.master", "local");
    ConversionTarget tc = ConversionTargetFactory.getInstance().createForFormat(targetTable, conf);
    assertEquals(tc.getTableFormat(), TableFormat.DELTA);
  }

  private TargetTable getPerTableConfig(String tableFormat) {
    return TargetTable.builder()
        .name(getTableName())
        .basePath("/tmp/doesnt/matter")
        .formatName(tableFormat)
        .build();
  }
}
