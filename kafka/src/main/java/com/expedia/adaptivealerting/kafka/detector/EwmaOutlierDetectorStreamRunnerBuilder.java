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
package com.expedia.adaptivealerting.kafka.detector;

import com.expedia.adaptivealerting.anomdetect.EwmaAnomalyDetector;
import com.expedia.adaptivealerting.kafka.util.AppUtil;
import com.expedia.adaptivealerting.kafka.util.DetectorUtil;
import com.expedia.adaptivealerting.kafka.util.StreamRunnerBuilder;
import com.expedia.www.haystack.commons.kstreams.app.StreamsRunner;
import com.typesafe.config.Config;
import org.apache.kafka.streams.StreamsBuilder;

public class EwmaOutlierDetectorStreamRunnerBuilder implements StreamRunnerBuilder {
  @Override
  public StreamsRunner build(Config appConfig) {
    final StreamsBuilder builder = DetectorUtil.createDetectorStreamsBuilder(
            appConfig.getString("topic"),
            id -> new EwmaAnomalyDetector(0.8, 2.0, 3.0, 100.0)
    );
    return AppUtil.createStreamsRunner(appConfig, builder);
  }
}
