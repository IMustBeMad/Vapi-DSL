package validation.api.demo.data.validator.attachment;

import validation.api.demo.data.common.Claim;
import validation.api.demo.validation.Validation;
import validation.api.demo.validation.domain.number.LongConditions;

public class AttachmentValidatorNew {

    public void validate() {
        Validation.verifyIf(new Claim())
                  .inspecting(Claim::getId, () -> LongConditions.isEqualTo(42L))
                  .or()
                  .isEqualTo(new Claim(), "error")
                  .failIfNoneGroupMatch();
    }
}
