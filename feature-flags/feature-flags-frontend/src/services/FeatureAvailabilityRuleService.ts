import {FeatureAvailabilityRuleModel, NamespaceRequest} from '../types/external';

export const getAllFeatureAvailabilityRules = async (): Promise<FeatureAvailabilityRuleModel[]> => {
    const response = await fetch('/api/feature-availability-rule');
    return await response.json()
}

export const getFeatureAvailabilityRule = async (id: string): Promise<FeatureAvailabilityRuleModel> => {
    const response = await fetch(`/api/feature-availability-rule/${id}`);
    return await response.json()
}

export const getAttributes = async (): Promise<string[]> => {
    const response = await fetch('/api/feature-availability-rule/attributes');
    return await response.json()
}

export const saveFeatureAvailabilityRule = async (data: FeatureAvailabilityRuleModel): Promise<void> => {
    await fetch('/api/feature-availability-rule', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export const updateFeatureAvailabilityRule = async (data: FeatureAvailabilityRuleModel): Promise<void> => {
    await fetch('/api/feature-availability-rule', {
        method: "PATCH",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export const deleteFeatureAvailabilityRule = async (id: string): Promise<void> => {
    await fetch(`/api/feature-availability-rule/${id}`, {
        method: "DELETE",
    });
}

export const getAllNamespaces = async (): Promise<string[]> => {
    const response = await fetch('/api/namespace');
    return await response.json()
}

export const deleteNamespace = async (namespace: string): Promise<void> => {
    await fetch(`/api/namespace/${namespace}`, {
        method: "DELETE",
    });
}

export const saveNamespace = async (namespace: NamespaceRequest): Promise<void> => {
    await fetch('/api/namespace', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(namespace)
    });
}

export const isUserAllowed = async (namespace: string, feature: string, userId: string): Promise<string> => {
    const response = await fetch(`api/feature-flag?namespace=${namespace}&feature=${feature}&userId=${userId}`)
    return response.text();
}
