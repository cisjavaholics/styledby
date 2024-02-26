package cis.javaholics.models.saves;

import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ASaves {
    private String saveId;
    private Timestamp savedAt;

    public void setPostedAt(String savedAt) throws ParseException {
        this.savedAt = Timestamp.fromProto(Timestamps.parse(savedAt));
    }

}
