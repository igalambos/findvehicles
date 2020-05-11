package com.example.cars.dataload;

import com.example.cars.exception.FieldFormatException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FieldConverter {

    /**
     * Currently the possible inputs we receive for mileage are either:
     * - the mileage as a number, e.g. 25000
     * - the mileage divided by 1000 suffixed with 'k', e.g. 25k = 25000
     *
     * @param mileageString the described field as a String
     * @return the field converted to Long
     */
    public Long convertMileage(String mileageString) throws FieldFormatException {
        if (StringUtils.isEmpty(mileageString)) {
            return 0L;
        }
        try {
            if (mileageString.contains("k")) {
                String withoutK = mileageString.replace("k", "");
                return Long.parseLong(withoutK) * 1000L;
            } else {
                return Long.parseLong(mileageString);
            }
        } catch (NumberFormatException e) {
            throw new FieldFormatException("Failed to convert mileage: " + mileageString, e);
        }
    }

    /**
     * Currently the possible inputs we receive for price are either:
     * - the price with an optional decimal full stop in the middle, e.g. 256.45
     * - the price with an optional decimal full stop in the middle prefixed with '£', e.g. £256.45
     *
     * @param priceString the described field as a String
     * @return the field converted to Float
     */
    public Float convertPrice(String priceString) throws FieldFormatException {
        if (StringUtils.isEmpty(priceString)) {
            return 0F;
        }
        if (priceString.contains("£")) {
            priceString = priceString.replace("£", "");
        }
        try {
            return Float.parseFloat(priceString);
        } catch (NumberFormatException e) {
            throw new FieldFormatException("Failed to convert price: " + priceString, e);
        }
    }

    /**
     * Currently we always receive the term as a number
     *
     * @param termString the described field as a String
     * @return the field converted to Long
     */
    public Long convertTerm(String termString) throws FieldFormatException {
        try {
            return StringUtils.isEmpty(termString) ? 0L : (long) Double.parseDouble(termString);
        } catch (NumberFormatException e) {
            throw new FieldFormatException("Failed to convert term: " + termString, e);
        }
    }

    /**
     * @param text the described field as a String
     * @return
     */
    public String convertText(String text) throws FieldFormatException {
        return text != null ? text.toLowerCase() : "";
    }
}
