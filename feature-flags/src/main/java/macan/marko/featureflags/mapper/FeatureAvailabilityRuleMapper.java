package macan.marko.featureflags.mapper;

import macan.marko.featureflags.entity.AndOperation;
import macan.marko.featureflags.entity.FeatureAvailabilityRule;
import macan.marko.featureflags.entity.OrOperation;
import macan.marko.featureflags.model.AndOperationModel;
import macan.marko.featureflags.model.FeatureAvailabilityRuleModel;
import macan.marko.featureflags.model.OrOperationModel;

import java.util.ArrayList;
import java.util.List;

public final class FeatureAvailabilityRuleMapper {

    private FeatureAvailabilityRuleMapper() {
    }

    public static FeatureAvailabilityRuleModel toModel(FeatureAvailabilityRule rule) {
        FeatureAvailabilityRuleModel model = new FeatureAvailabilityRuleModel();
        model.setId(rule.getId());
        model.setNamespace(rule.getNamespace().getNamespace());
        model.setFeature(rule.getFeature());
        model.setDescription(rule.getDescription());
        model.setActive(rule.getActive());

        List<OrOperationModel> orOperations = new ArrayList<>();
        for (OrOperation orOperation : rule.getOrOperations()) {
            OrOperationModel orOperationModel = new OrOperationModel();
            List<AndOperationModel> andOperations = new ArrayList<>();
            for (AndOperation andOperation : orOperation.getAndOperations()) {
                AndOperationModel andOperationModel = new AndOperationModel();
                andOperationModel.setGroupStrategy(andOperation.getGroupStrategy());
                andOperationModel.setCondition(andOperation.getCondition());
                andOperationModel.setCriteria(andOperation.getCriteria());
                andOperations.add(andOperationModel);
            }
            orOperationModel.setAndOperations(andOperations);
            orOperations.add(orOperationModel);
        }
        model.setOrOperations(orOperations);

        return model;
    }
}
