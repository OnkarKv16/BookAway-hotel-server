package com.mywork.bookAwayhotel.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mywork.bookAwayhotel.model.Room;
import com.mywork.bookAwayhotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RoomServiceImple implements IRoomService{


		
	private final RoomRepository roomRepository;
	@Override
	public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
		
		Room room = new Room();
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		
		if(!file.isEmpty()) {
			byte[] photoBytes = file.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
		
		return roomRepository.save(room);
	}
	
}

