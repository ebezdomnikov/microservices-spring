package com.ac.microservices.model;

public enum ProductType {
    INLINE {
        @Override
        public String toString() {
            return "inline";
        }
    },
}
