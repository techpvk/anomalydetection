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
package com.expedia.adaptivealerting.modelservice.service.impl;

import com.expedia.adaptivealerting.modelservice.dto.ModelDto;
import com.expedia.adaptivealerting.modelservice.dto.ModelParams;
import com.expedia.adaptivealerting.modelservice.entity.Metric;
import com.expedia.adaptivealerting.modelservice.entity.Model;
import com.expedia.adaptivealerting.modelservice.repo.ModelRepository;
import com.expedia.adaptivealerting.modelservice.repo.ModelRepositoryCustom;
import com.expedia.adaptivealerting.modelservice.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author kashah
 * @author shsethi
 */

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepositoryCustom modelRepositoryCustom;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<ModelDto> getModels(String metricKey) {
        return modelRepositoryCustom.findModels(metricKey);
    }

    @Override
    public void addModelParams(ModelParams modelParams) {

        Model model = new Model(modelParams.getModelUUID(), modelParams.getHyperparams());
        Metric metric = new Metric(modelParams.getMetricKey());
        model.getMetrics().add(metric);
        metric.getModels().add(model);
        modelRepository.save(model);
    }

    @Override
    public void markToRebuild(String modelUUID, String metricKey, Boolean toRebuild) {

        Integer modelID = modelRepositoryCustom.getModelID(metricKey, modelUUID);
        Model model = modelRepository.getModelById(modelID);
        model.setToRebuild(toRebuild);
        modelRepository.save(model);

    }

    @Override
    public void updateThresholds(String modelUUID, String metricKey, Map<String, Object> thresholds) {

        Integer modelID = modelRepositoryCustom.getModelID(metricKey, modelUUID);
        Model model = modelRepository.getModelById(modelID);
        model.setThresholds(thresholds);
        modelRepository.save(model);
    }
}