package cis.javaholics.models.saves;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ASaves {
    @DocumentId
    @Schema(example = "12345", description = "Unique ID of the save")
    private String saveId;
    @Schema(example = "January 25, 2024 at 6:56:59PM", description = "Time that post was saved")
    private Timestamp savedAt;

    public void setPostedAt(String savedAt) throws ParseException {
        this.savedAt = Timestamp.fromProto(Timestamps.parse(savedAt));
    }

}
