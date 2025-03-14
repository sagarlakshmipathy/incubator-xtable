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
 
package org.apache.xtable.exception;

import org.apache.xtable.model.exception.ErrorCode;
import org.apache.xtable.model.exception.InternalException;

/**
 * CatalogSyncException should be used for failures that occur while performing {@link
 * org.apache.xtable.spi.sync.CatalogSyncClient} operations
 */
public class CatalogSyncException extends InternalException {

  public CatalogSyncException(ErrorCode errorCode, String message, Throwable e) {
    super(errorCode, message, e);
  }

  public CatalogSyncException(String message, Throwable e) {
    super(ErrorCode.CATALOG_SYNC_GENERIC_EXCEPTION, message, e);
  }

  public CatalogSyncException(String message) {
    super(ErrorCode.CATALOG_SYNC_GENERIC_EXCEPTION, message);
  }
}
