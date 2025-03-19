package au.com.telstra.simcardactivator.model;

import javax.persistence.*;

@Entity
    @Table(name = "sim_card_record")
    public class SimCardRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false, unique = true)
        private String iccid;

        @Column(nullable = false)
        private String customerEmail;

        @Column(nullable = false)
        private boolean active;

        protected SimCardRecord() {
        }

        public SimCardRecord(SimCard simCard, ActuationResult actuationResult) {
            this.iccid = simCard.getIccid();
            this.customerEmail = simCard.getCustomerEmail();
            this.active = actuationResult.getSuccess();
        }

        public long getId() {
            return id;
        }

        public String getIccid() {
            return iccid;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public boolean isActive() {
            return active;
        }

        @Override
        public String toString() {
            return "SimCardRecord {id=" + id + ", iccid=" + iccid + ", customerEmail=" + customerEmail + ", active=" + active + "}";
        }
    }

