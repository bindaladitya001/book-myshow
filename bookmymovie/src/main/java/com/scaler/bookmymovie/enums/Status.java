package com.scaler.bookmymovie.enums;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum Status {
    AVAILABLE,
    BOOKED,
    LOCKED

}
