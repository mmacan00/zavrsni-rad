/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.19.577 on 2020-05-18 21:55:16.

export interface AndOperationModel {
    groupStrategy: string;
    condition: GroupCondition;
    criteria: string;
}

export interface EligibleUser {
    id: string;
    userName: string;
}

export interface FeatureAvailabilityRuleModel {
    id: string;
    feature: string;
    namespace: string;
    description: string;
    active: boolean;
    orOperations: OrOperationModel[];
}

export interface Filter {
    filterMatrix: FilterClause[][];
}

export interface FilterClause {
    column: string;
    condition: string;
    criteria: string;
}

export interface NamespaceUpdateRequest {
    oldNamespaceName: string;
    newNamespaceName: string;
}

export interface OrOperationModel {
    andOperations: AndOperationModel[];
}

export enum GroupCondition {
    IS = "IS",
    IS_NOT = "IS_NOT",
}
