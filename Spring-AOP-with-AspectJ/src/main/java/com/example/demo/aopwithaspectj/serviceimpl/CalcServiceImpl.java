package com.example.demo.aopwithaspectj.serviceimpl;

import org.springframework.stereotype.Service;

import com.example.demo.aopwithaspectj.service.CalcService;

@Service
public class CalcServiceImpl implements CalcService {
	
	@Override
    public int sum(int x, int y) {
        return x + y;
    }

    @Override
    public int subtract(int x, int y) {
        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        return x * y;
    }

    @Override
    public int divide(int x, int y) {
        return x / y;
    }
}
