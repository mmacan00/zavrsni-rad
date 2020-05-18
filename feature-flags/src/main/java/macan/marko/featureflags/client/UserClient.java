package macan.marko.featureflags.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import macan.marko.featureflags.entity.AndOperation;
import macan.marko.featureflags.entity.OrOperation;
import macan.marko.featureflags.model.EligibleUser;
import macan.marko.featureflags.model.Filter;
import macan.marko.featureflags.model.FilterClause;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class UserClient {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient();

    private UserClient() {
    }

    public static List<String> getUsersMatchingFilter(Filter filter) throws IOException {
        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(filter),
                MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("http://localhost:8081/api/users/filter")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            return Arrays.stream(objectMapper.readValue(response.body().string(), EligibleUser[].class))
                    .map(EligibleUser::getId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static Filter mapToFilter(List<OrOperation> orOperations) {
        List<List<FilterClause>> filterMatrix = new ArrayList<>();
        for (OrOperation orOperation : orOperations) {
            List<FilterClause> filterClauses = new ArrayList<>();
            for (AndOperation andOperation : orOperation.getAndOperations()) {
                FilterClause clause = new FilterClause(
                        andOperation.getGroupStrategy(),
                        andOperation.getCondition().getSqlOperator(),
                        andOperation.getCriteria());
                filterClauses.add(clause);
            }
            filterMatrix.add(filterClauses);
        }
        return new Filter(filterMatrix);
    }

    public static List<String> getAttributes() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8081/api/users/attributes")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.isSuccessful() && response.body() != null) {
            return Arrays.asList(objectMapper.readValue(response.body().string(), String[].class));
        }
        return Collections.emptyList();
    }
}
