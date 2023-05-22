package kubg.edu.ua.automation.pharmacy.service;

import kubg.edu.ua.automation.pharmacy.annotation.Public;
import kubg.edu.ua.automation.pharmacy.dto.UserOrderDto;
import kubg.edu.ua.automation.pharmacy.mapper.UserOrderMapper;
import kubg.edu.ua.automation.pharmacy.repository.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final UserOrderRepository repository;
    private final UserOrderMapper mapper;

    @Public
    public List<UserOrderDto> getUserOrders(Long userId) {
        AtomicLong orderCounter = new AtomicLong();
        return repository.findByUserIdOrderByTimeDesc(userId)
                .stream()
                .map(mapper::toDto)
                .peek(order -> order.setNumber(orderCounter.incrementAndGet()))
                .toList();
    }

}
