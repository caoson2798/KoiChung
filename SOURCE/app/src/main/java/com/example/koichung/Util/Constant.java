package com.example.koichung.Util;

public class Constant {
    public static final int MNU_ADMIN = 1;
    public static final int MNU_ADD = 1990;
    public static String KEY_SELECT_TYPE = "tpye";

    //trạng thái của lô hàng
    public enum STATUS_BATH {
        STATUS_ALL_BATCH(0),
        STATUS_HAVAE_CONTRACT(1),
        STATUS_NOT_CONTRACT(-1);

        public int values;

        STATUS_BATH(int values) {
            this.values = values;
        }
    }

    // Vai trò của use
    public enum ROLE_USER {
        ROLE_AGNECY(2),
        ROLE_ADMIN(1);


        public int values;

        ROLE_USER(int values) {
            this.values = values;
        }
    }

    // trạng thái của hợp đồng
    public enum STATUS_CONTRACT {
        STATUS_DESTROY_CONTRACTS(-1),
        STATUS_ALL_CONTRACT(10),
        STATUS_WAITING_APPROVE_CONTRACT(0),
        STATUS_OPEN_CONTRACT(1),
        STATUS_COMPLETE_CONTRACT(2),
        STATUS_OVER_DUE_CONTRACT(-2);

        public int values;

        STATUS_CONTRACT(int values) {
            this.values = values;
        }
    }

    public enum SELECT_TYPE {
        CHOSSE_AGENCY_ALL(0),
        CHOSSE_BATCH_ALL(1),
        CREATE_BATCH(2),
        CHOSSE_CONTRACT_ALL(3),
        CHOSSE_BATCH(4),
        CHOSSE_AGENCY(5);

        /**
         * Value for this difficulty
         */
        public int Value;

        private SELECT_TYPE(int value) {
            Value = value;
        }
    }

    public enum TYPE_COMIT_CONTRACT {
        TYPE_FUNDS(1),
        TYPE_PROFIT(0);

        /**
         * Value for this difficulty
         */
        public int Value;

        private TYPE_COMIT_CONTRACT(int value) {
            Value = value;
        }
    }


}
