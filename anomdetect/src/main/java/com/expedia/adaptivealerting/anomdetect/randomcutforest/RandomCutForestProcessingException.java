/*
 * Copyright 2018 Expedia Group, Inc.
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
package com.expedia.adaptivealerting.anomdetect.randomcutforest;


/**
 * <p>
 * Exception reserved for errors while processing requests to Random Cut Forest endpoint in AWS.
 * </p>
 *
 * @author Tatjana Kamenov
 */
public class RandomCutForestProcessingException extends RuntimeException
{
    public RandomCutForestProcessingException(String message)
    {
        super(message);
    }

    public RandomCutForestProcessingException(String message, Throwable cause)
    {
        super(message, cause);
    }
}