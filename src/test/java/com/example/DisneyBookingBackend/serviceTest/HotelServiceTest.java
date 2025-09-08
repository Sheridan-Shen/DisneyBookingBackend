package com.example.DisneyBookingBackend.serviceTest;

import com.example.DisneyBookingBackend.models.Hotel;
import com.example.DisneyBookingBackend.models.Theme;
import com.example.DisneyBookingBackend.repository.hotel.HotelDBRepository;
import com.example.DisneyBookingBackend.repository.theme.ThemeDBRepository;
import com.example.DisneyBookingBackend.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

@ExtendWith(SpringExtension.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelDBRepository hotelDBRepository;

    @Mock
    private ThemeDBRepository themeDBRepository;

    @Test
    public void testGetAllHotels() {
        // given
        Theme theme1 = new Theme("Adventure");
        theme1.setId(1);

        Theme theme2 = new Theme("Fantasy");
        theme2.setId(2);

        // mock Hotel
        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setHotelName("Hotel California");
        hotel.setAddress("123 Sunset Blvd");
        hotel.setDescription("A lovely place");
        hotel.setRatings(5.0f);
        hotel.setThemes(Arrays.asList(theme1, theme2));

        // mock repository
        Mockito.when(hotelDBRepository.getAllHotels()).thenReturn(Arrays.asList(hotel));

        // call service
        List<Hotel> hotels = hotelService.getAllHotels();

        // assertions
        assertNotNull(hotels);
        assertEquals(1, hotels.size());
        assertEquals("Hotel California", hotels.get(0).getHotelName());
        assertEquals(2, hotels.get(0).getThemes().size());
        assertEquals("Adventure", hotels.get(0).getThemes().get(0).getThemeName());
    }

    @Test
    public void testAddHotel() {
        // given
        Theme theme = new Theme("Adventure");
        List<Theme> themeList = new ArrayList<>();
        themeList.add(theme);

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setHotelName("Hotel California");
        hotel.setAddress("上海市");
        hotel.setDescription("A lovely place");
        hotel.setRatings(5.0f);
        hotel.setThemes(themeList);


        // mock repository
        // mock themeDBRepository: 假设数据库没有该主题，需新建
        Mockito.when(themeDBRepository.findByThemeName("Adventure")).thenReturn(null);
        // mock themeDBRepository.save
        Mockito.when(themeDBRepository.save(Mockito.any(Theme.class))).thenReturn(theme);
        Mockito.when(hotelDBRepository.save(hotel)).thenReturn(hotel);


        // call service
        Hotel savedHotel = hotelService.addHotel(hotel);

        // assertions
        print(savedHotel);
        assertNotNull(savedHotel);
        assertEquals("Hotel California", savedHotel.getHotelName());
        assertEquals(1, savedHotel.getThemes().size());
        assertEquals("Adventure", savedHotel.getThemes().get(0).getThemeName());
    }
}
