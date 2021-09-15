SELECT o.freetext, h.name, o.date_time
FROM observations o, healthcare_professionals h
WHERE o.freetext LIKE '%breathing%'
AND o.hid = h.hid
AND o.patientid IN
  (SELECT patientid FROM patients
  WHERE name = 'Samantha Adam'
);;
