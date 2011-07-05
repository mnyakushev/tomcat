/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina.tribes.group.interceptors;

import java.util.Arrays;

import junit.framework.TestCase;

public class TestGzipInterceptor extends TestCase {

    public void testSmallerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE / 2);
    }

    public void testJustSmallerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE -1);
    }

    public void testExactBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE);
    }

    public void testJustLargerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE + 1);
    }

    public void testFactor2BufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 2);
    }

    public void testFactor4BufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 4);
    }

    public void testMuchLargerThanBufferSize() throws Exception {
        doCompressDecompress(GzipInterceptor.DEFAULT_BUFFER_SIZE * 10 + 1000);
    }

    private void doCompressDecompress(int size) throws Exception {
        byte[] data = new byte[size];
        Arrays.fill(data, (byte)1);
        byte[] compress = GzipInterceptor.compress(data);
        byte[] result = GzipInterceptor.decompress(compress);
        assertTrue(Arrays.equals(data, result));
    }
}
