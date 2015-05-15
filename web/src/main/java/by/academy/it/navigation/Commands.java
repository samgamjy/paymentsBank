package by.academy.it.navigation;

import by.academy.it.navigation.commands.*;

public enum Commands {
    CREATE_CLIENT {
        @Override
        public Command getCommand() {
            return new CreateClientCommand();
        }
    },

    READ_CLIENT {
        @Override
        public Command getCommand() {
            return new ReadClientCommand();
        }
    },

    LIST_CLIENTS {
        @Override
        public Command getCommand() {
            return new ListClientsCommand();
        }
    },

    CREATE_BANK_ACCOUNT{
        @Override
        public Command getCommand() {
            return new CreateBankAccountCommand();
        }
    },


    LOGIN_USER {
        @Override
        public Command getCommand() {
            return new LoginUserCommand();
        }
    },

    BLOCK_CREDIT_CARD{
        @Override
        public Command getCommand() {
            return new BlockCreditCardCommand();
        }
    },

    TRANSFER_MONEY{
        @Override
        public Command getCommand() {
            return new TransferMoneyCommand();
        }
    },

    PROFILE_CLIENT{
        @Override
        public Command getCommand() {
            return new ProfileClientCommand();
        }
    },

    CREATE_ORDER{
        @Override
        public Command getCommand() {
            return new CreateOrderCommand();
        }
    },

    LIST_ORDER{
        @Override
        public Command getCommand() {
            return new ListOrderByBankAccountCommand();
        }
    },

    CONTROL_CLIENT{
        @Override
        public Command getCommand() {
            return new ControlClientCommand();
        }
    }

    ;

    public abstract Command getCommand();
}
