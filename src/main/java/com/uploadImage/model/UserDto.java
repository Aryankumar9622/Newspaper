package com.uploadImage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	   @Column
	    private String name;
	    @Column
	    private String type;

	    @Lob
	    @Column(name = "profile_photo")
	    private byte[] profilePhoto;

}


