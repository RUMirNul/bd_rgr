package edu.ansvistunov.command;

import edu.ansvistunov.command.commands.*;

public enum CommandEnum {

    ADD_NEW_COMPANY_COMMAND(new AddNewCompanyCommand()),
    GET_COMPANY_BY_KPP(new GetCompanyCommand()),
    DELETE_COMPANY_BY_KPP(new DeleteCompanyCommand()),
    FIND_ALL_COMPANY_BY_NAME(new FindAllCompanyByNameCommand()),
    ADD_NEW_INSURANCE_TYPE(new AddNewInsuranceTypeCommand()),
    GET_INSURANCE_TYPE_BY_CODE(new GetInsuranceTypeCommand()),
    DELETE_INSURANCE_TYPE_BY_CODE(new DeleteInsuranceTypeCommand()),
    ADD_NEW_CLIENT(new AddNewClientCommand()),
    GET_CLIENT_BY_NUMBER(new GetClientByNumberCommand()),
    DELETE_CLIENT_BY_NUMBER(new DeleteClientByNumberCommand()),
    GET_ALL_CLIENT_PAYMENTS(new GetAllClientPaymentsCommand()),
    ADD_NEW_POLICY(new AddNewPolicyCommand()),
    GET_POLICY_BY_ID(new GetPolicyByIdCommand()),
    DELETE_POLICY_BY_ID(new DeletePolicyByIdCommand()),
    ADD_NEW_PAYMENT(new AddNewPaymentCommand()),
    GET_PAYMENT_BY_ID(new GetPaymentByIdCommand()),
    DELETE_PAYMENT_BY_ID(new DeletePaymentByIdCommand()),
    GET_ALL_PAYMENTS_BY_DATE(new GetAllPaymentsByDateCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
